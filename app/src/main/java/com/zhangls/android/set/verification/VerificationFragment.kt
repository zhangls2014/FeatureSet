package com.zhangls.android.set.verification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zhangls.android.set.R
import kotlinx.android.synthetic.main.fragment_verification.*


/**
 * 验证码输入 示例 [Fragment]
 *
 * @author zhangls
 */
class VerificationFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_verification, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    inputBoxSquare.onInputComplete = {
      Toast.makeText(context, "验证码是：$it", Toast.LENGTH_SHORT).show()
    }
    inputBoxLine.onInputComplete = {
      Toast.makeText(context, "验证码是：$it", Toast.LENGTH_SHORT).show()
    }
  }


  companion object {
    /**
     * 返回一个 Fragment 实例
     */
    fun newInstance(): VerificationFragment = VerificationFragment()
  }
}
