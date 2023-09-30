import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.androidintesive.R
import com.genrikhsalexandr.androidintesive.databinding.FragmentABinding
import com.genrikhsalexandr.androidintesive.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding: FragmentBBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBBinding.inflate(inflater, container, false)

        with(binding) {
            btFragmentC.setOnClickListener {
                val fragmentC = FragmentC()
                val args = Bundle()
                args.putString("message", "Hello Fragment C")
                fragmentC.arguments = args
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.containerFragment, fragmentC)
                transaction.addToBackStack(null)
                transaction.commit()

                btBackA.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}