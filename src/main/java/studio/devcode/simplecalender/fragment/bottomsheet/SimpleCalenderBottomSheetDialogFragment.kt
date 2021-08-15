package studio.devcode.simplecalender.fragment.bottomsheet

import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import studio.devcode.simplecalender.CalenderViewDialogBuilder
import studio.devcode.simplecalender.R
import studio.devcode.simplecalender.databinding.SimpleCalenderFragmentBinding
import java.util.*


class SimpleCalenderBottomSheetDialogFragment(private val builder: CalenderViewDialogBuilder) :
    BottomSheetDialogFragment() {
    private lateinit var viewModel: SimpleCalenderViewModel
    private lateinit var binding: SimpleCalenderFragmentBinding
    private lateinit var calender: Calendar
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpleCalenderFragmentBinding.inflate(layoutInflater)

        /*init bottom sheet*/
     /*  bottomSheetBehavior = BottomSheetBehavior.from(binding.root)*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calender = Calendar.getInstance()
        viewModel = ViewModelProvider(this).get(SimpleCalenderViewModel::class.java)

        /*init the methods */
        initFunc()
    }

    private fun initFunc() {
        onStateChange()
        getStates()
    }

    private fun onStateChange() {
       /* bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    viewModel.mutableCalenderData.value = SimpleCalenderViewModel.CalenderState.Open
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED ||
                    newState == BottomSheetBehavior.STATE_HIDDEN
                ) {
                    viewModel.mutableCalenderData.value =
                        SimpleCalenderViewModel.CalenderState.Close
                }
            }
        })*/
        binding.calenderView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calender.set(year, month, dayOfMonth)
            viewModel.mutableCalenderData.value =
                SimpleCalenderViewModel.CalenderState.OnDateSelected(
                    (calender.timeInMillis)
                )
        }

        binding.collapseFromTop.setOnClickListener {
            dismiss()
        }
    }

    /*created for later more complex implementations*/
    private fun getStates() {
        viewModel.initState()
        lifecycleScope.launchWhenCreated {
            viewModel.calenderStateFlow.collect {
                when (it) {
                    is SimpleCalenderViewModel.CalenderState.OnDateSelected -> {
                        builder.getCallback().onDateSelected(it.selectedDate)
                    }

                    is SimpleCalenderViewModel.CalenderState.Open -> {
                        builder.getCallback().onCalenderOpened()
                    }

                    is SimpleCalenderViewModel.CalenderState.Close -> {
                        builder.getCallback().onCalenderDismissed()
                    }

                    is SimpleCalenderViewModel.CalenderState.Error -> {
                        builder.getCallback().onError(it.errorMessage)
                    }
                }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}