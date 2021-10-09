package com.wayyan.countriessqldelight.features

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.wayyan.countriessqldelight.R
import com.wayyan.countriessqldelight.adapter.CountryRecyclerAdapter
import com.wayyan.countriessqldelight.base.core.BaseActivity
import com.wayyan.countriessqldelight.base.core.BaseDelegate
import com.wayyan.countriessqldelight.base.core.Code
import com.wayyan.countriessqldelight.base.helper.AsyncViewState
import com.wayyan.countriessqldelight.databinding.ActivityMainBinding
import com.wayyan.countriessqldelight.domain.model.CountryModel
import com.wayyan.countriessqldelight.extensions.getLoadingDialog
import com.wayyan.countriessqldelight.extensions.showShortToast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModel()
    private val countryRecyclerAdapter: CountryRecyclerAdapter by inject()

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initListener()

        viewModel.doGetCountries()
        viewModel.fetchAllCountries()
    }

    private fun initUi() {
        loadingDialog = this.getLoadingDialog(resources.getString(R.string.lbl_loading))
        initRecycler()
    }

    private fun initListener() {
        viewModel.fetchAllCountriesLiveData.observe(this, {
            when (it) {
                is AsyncViewState.Loading -> {
                    loadingDialog.show()
                }

                is AsyncViewState.Error -> {
                    loadingDialog.dismiss()
                    this.showShortToast(it.errorMessage)
                }

                is AsyncViewState.Success -> {
                    if (it.value.isEmpty()) {
                        loadingDialog.dismiss()
                    } else {
                        countryRecyclerAdapter.setNewData(it.value)
                    }
                }
            }
        })

        viewModel.getCountriesLiveData.observe(this, {
            when (it) {
                is AsyncViewState.Loading -> {
                }

                is AsyncViewState.Error -> {
                    this.showShortToast(it.errorMessage)
                }

                is AsyncViewState.Success -> {
                    countryRecyclerAdapter.setNewData(it.value)
                }
            }
        })

    }

    private fun initRecycler() {
        countryRecyclerAdapter.addDelegate(object : BaseDelegate<CountryModel> {
            override fun onAction(code: Code, data: CountryModel?) {
                this@MainActivity.showShortToast("Is Country = ${data!!.isCountry}")
            }

        })

        binding.recyclerCountry.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = countryRecyclerAdapter
        }
    }
}