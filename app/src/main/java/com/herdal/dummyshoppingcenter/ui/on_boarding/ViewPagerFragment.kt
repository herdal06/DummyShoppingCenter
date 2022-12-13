package com.herdal.dummyshoppingcenter.ui.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.herdal.dummyshoppingcenter.databinding.FragmentViewPagerBinding
import com.herdal.dummyshoppingcenter.ui.on_boarding.screens.FirstScreen
import com.herdal.dummyshoppingcenter.ui.on_boarding.screens.SecondScreen
import com.herdal.dummyshoppingcenter.ui.on_boarding.screens.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = setupViewPagerAdapter()
    }

    private fun setupFragmentList(): ArrayList<Fragment> {
        return arrayListOf(
            FirstScreen(),
            SecondScreen(),
        )
    }

    private fun setupViewPagerAdapter(): ViewPagerAdapter = ViewPagerAdapter(
        setupFragmentList(),
        requireActivity().supportFragmentManager,
        lifecycle
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}