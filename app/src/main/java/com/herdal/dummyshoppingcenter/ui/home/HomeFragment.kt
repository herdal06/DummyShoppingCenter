package com.herdal.dummyshoppingcenter.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.databinding.FragmentHomeBinding
import com.herdal.dummyshoppingcenter.ui.home.adapter.categories.CategoryAdapter
import com.herdal.dummyshoppingcenter.ui.home.adapter.products.ProductAdapter
import com.herdal.dummyshoppingcenter.utils.ext.hide
import com.herdal.dummyshoppingcenter.utils.ext.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(::onProductClick)
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        collectApiRequest()
        //collectProducts()
        addMenuProvider()
    }

    private fun addMenuProvider() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_switch_layout_manager -> {
                        switchLayoutManager()
                        true
                    }
                    R.id.action_switch_to_dark_mode -> {
                        switchAppMode()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun switchAppMode() {
        TODO("Not yet implemented")
    }

    private fun switchLayoutManager() {
        TODO("Not yet implemented")
    }

    private fun collectCategories() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // categories
                    viewModel.getAllCategories()
                    viewModel.categories.collect { res ->
                        when (res) {
                            is Resource.Loading -> {
                                textViewCategoryError.hide()
                                recyclerViewCategories.hide()
                            }
                            is Resource.Success -> {
                                textViewCategoryError.hide()
                                progressBarCategories.hide()
                                recyclerViewCategories.show()
                                categoryAdapter.submitList(res.data)
                            }
                            is Resource.Error -> {
                                progressBarCategories.hide()
                                textViewCategoryError.show()
                                recyclerViewCategories.hide()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun collectApiRequest() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            binding.apply {
                viewModel.getProducts().observe(viewLifecycleOwner) {
                    productAdapter.submitData(lifecycle, it)
                    Timber.d("$it")
                }
            }
        }
    }

    private fun onProductClick(productId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(productId)
        findNavController().navigate(action)
    }

    private fun setupRecyclerViews() = binding.apply {
        recyclerViewProducts.adapter = productAdapter
        recyclerViewCategories.adapter = categoryAdapter
        collectCategories()
    }
}