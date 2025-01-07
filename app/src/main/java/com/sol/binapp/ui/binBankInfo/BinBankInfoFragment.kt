package com.sol.binapp.ui.binBankInfo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sol.binapp.databinding.FragmentBinBankInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BinBankInfoFragment : Fragment() {
    private val binding by lazy { FragmentBinBankInfoBinding.inflate(layoutInflater) }
    private val viewModel: BinBankInfoViewModel by viewModels()
    private val args: BinBankInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binDataUploaded()
        initUI()
    }

    private fun binDataUploaded() {
        binding.apply {
            tvBinNumberDescription.visibility = View.GONE
            tvBinNumber.visibility = View.GONE
            tvBankNameDescription.visibility = View.GONE
            tvBankName.visibility = View.GONE
            tvCountry.visibility = View.GONE
            tvCountryEmoji.visibility = View.GONE
            tvCountryName.visibility = View.GONE
            tvCurrencyDescription.visibility = View.GONE
            tvCurrency.visibility = View.GONE
            tvCoordinatesDescription.visibility = View.GONE
            tvCoordinates.visibility = View.GONE
            tvOpenMap.visibility = View.GONE
            tvEmptyPlaceholderBankInfo.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val binNumberArgs = args.binNumber
        viewModel.loadBinBankInfo(binNumberArgs)

        viewModel.binBankInfoState.observe(viewLifecycleOwner) { state ->
            val latitude = state.bin?.country?.latitude.toString()
            val longitude = state.bin?.country?.longitude.toString()

            binding.tvOpenMap.setOnClickListener {
                openMap(latitude, longitude)
            }

            binding.tvBinNumber.text = binNumberArgs
            binding.tvBankName.text = state.bin?.bank?.name
            binding.tvCountryEmoji.text = state.bin?.country?.emoji
            binding.tvCountryName.text = state.bin?.country?.countryName
            binding.tvCurrency.text = state.bin?.country?.currency
            binding.tvCoordinates.text = "$latitude / $longitude"

            if (state.isLoading) {
                binding.apply {
                    tvBinNumberDescription.visibility = View.VISIBLE
                    tvBinNumber.visibility = View.VISIBLE
                    tvBankNameDescription.visibility = View.VISIBLE
                    tvBankName.visibility = View.VISIBLE
                    tvCountry.visibility = View.VISIBLE
                    tvCountryEmoji.visibility = View.VISIBLE
                    tvCountryName.visibility = View.VISIBLE
                    tvCurrencyDescription.visibility = View.VISIBLE
                    tvCurrency.visibility = View.VISIBLE
                    tvCoordinatesDescription.visibility = View.VISIBLE
                    tvCoordinates.visibility = View.VISIBLE
                    tvOpenMap.visibility = View.VISIBLE
                    tvEmptyPlaceholderBankInfo.visibility = View.GONE
                }
            }
        }
    }

    private fun openMap(latitude: String, longitude: String) {
        val geoUri = "https://www.google.com/maps?q=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearState()
    }
}
