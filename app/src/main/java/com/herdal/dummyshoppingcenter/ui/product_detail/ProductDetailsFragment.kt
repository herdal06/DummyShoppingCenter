package com.herdal.dummyshoppingcenter.ui.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.databinding.FragmentProductDetailsBinding
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.ui.product_detail.adapter.ImageSlideAdapter
import com.herdal.dummyshoppingcenter.utils.ext.getFormattedPrice
import com.herdal.dummyshoppingcenter.utils.ext.hide
import com.herdal.dummyshoppingcenter.utils.ext.show
import com.herdal.dummyshoppingcenter.utils.ext.showBalloon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ProductDetailsViewModel by viewModels()

    private val navigationArgs: ProductDetailsFragmentArgs by navArgs()

    lateinit var viewPagerAdapter: ImageSlideAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectProductDetailRequest()

    }

    private fun onClickFavButton(product: ProductUiModel) =
        binding.imageButtonFavDetails.setOnClickListener {
            Toast.makeText(requireContext(), "Saved to favorite!", Toast.LENGTH_LONG).show()
            viewModel.saveProductToDb(product)
        }

    private fun getArgs() = navigationArgs.productId

    private fun collectProductDetailRequest() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getProductById(getArgs())
                viewModel.productDetail.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            progressBarProductDetails.show()
                            textViewErrorProductDetails.hide()
                        }
                        is Resource.Success -> {
                            resource.data.let { product ->
                                setupUI(product)
                            }
                            textViewErrorProductDetails.hide()
                            progressBarProductDetails.hide()

                        }
                        is Resource.Error -> {
                            progressBarProductDetails.hide()
                            textViewErrorProductDetails.show()
                        }
                    }
                }
            }
        }
    }

    private fun setupUI(product: ProductUiModel?) = binding.apply {
        product?.let {
            textViewNameDetails.text = product.title
            textViewDescriptionDetails.text = product.description
            textViewBrandDetails.text = product.brand
            textViewCategoryDetails.text = product.category
            textViewPriceDetails.text = product.getFormattedPrice()
            ratingBar.rating = product.rating.toFloat()

            setupViewPager(product)

            onClickFavButton(product)

            binding.textViewBrandDetails.setOnClickListener {
                it.showBalloon(
                    requireContext(),
                    viewLifecycleOwner,
                    product.category,
                    textColor = R.color.orange,
                    bgColor = R.color.white
                )
            }
        }
    }

    private fun setupViewPager(product: ProductUiModel) = binding.apply {
        viewPagerAdapter = ImageSlideAdapter(requireContext(), product.images)
        viewPager2.adapter = viewPagerAdapter
        indicator.setViewPager(viewPager2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}