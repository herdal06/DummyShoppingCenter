package com.herdal.dummyshoppingcenter.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.databinding.FragmentHomeBinding
import com.herdal.dummyshoppingcenter.ui.home.adapter.products.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
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
        setupRecyclerView()
        collectApiRequest()
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

    private fun setupRecyclerView() = binding.apply {
        recyclerViewProducts.adapter = productAdapter
    }
}