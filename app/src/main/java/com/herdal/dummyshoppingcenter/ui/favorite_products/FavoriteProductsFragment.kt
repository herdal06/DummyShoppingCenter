package com.herdal.dummyshoppingcenter.ui.favorite_products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.databinding.FragmentFavoriteProductsBinding
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.ui.favorite_products.adapter.FavoriteProductAdapter
import com.herdal.dummyshoppingcenter.utils.ext.showBalloon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteProductsFragment : Fragment() {
    private var _binding: FragmentFavoriteProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val favoriteProductAdapter: FavoriteProductAdapter by lazy {
        FavoriteProductAdapter(::onProductClick)
    }

    private val viewModel: FavoriteProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeLiveData()
        swipeToDelete()
    }

    private fun swipeToDelete() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val product = favoriteProductAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.onItemSwiped(product)
            }
        }).attachToRecyclerView(binding.rvFavoriteProducts)
    }

    private fun observeLiveData() {
        viewModel.allFavProducts.observe(viewLifecycleOwner) { products ->
            products.let {
                favoriteProductAdapter.submitList(it)
            }
        }
        collectEvents()
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productsEvent.collect { event ->
                when (event) {
                    is FavoriteProductsViewModel.ProductsEvent.ShowUndoDeleteItemMessage -> {
                        Snackbar.make(
                            requireView(),
                            getString(R.string.product_deleted),
                            Snackbar.LENGTH_LONG
                        )
                            .setAction(getString(R.string.undo)) {
                                viewModel.onUndoDeleteClick(event.product)
                            }.show()
                    }
                }
            }
        }
    }

    private fun onProductClick(productId: Int) {
        val action =
            FavoriteProductsFragmentDirections.actionFavoriteProductsFragmentToProductDetailsFragment(
                productId
            )
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() = binding.apply {
        rvFavoriteProducts.adapter = favoriteProductAdapter
    }

    private fun onProductClick(view: View, product: ProductUiModel) {
        view.showBalloon(
            context = requireContext(),
            lifecycle = viewLifecycleOwner,
            balloonText = product.category,
            textColor = R.color.app_background_color,
            bgColor = R.color.orange,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}