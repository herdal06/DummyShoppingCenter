package com.herdal.dummyshoppingcenter.ui.favorite_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.herdal.dummyshoppingcenter.databinding.FragmentFavoriteProductsBinding
import com.herdal.dummyshoppingcenter.ui.home.adapter.products.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteProductsFragment : Fragment() {
    private var _binding: FragmentFavoriteProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val favoriteProductAdapter: ProductAdapter by lazy {
        ProductAdapter(::onProductClick)
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}