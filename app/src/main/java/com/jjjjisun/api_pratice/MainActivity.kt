package com.jjjjisun.api_pratice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.jjjjisun.api_pratice.databinding.ActivityMainBinding
import com.jjjjisun.api_pratice.retrofit.RetrofitManager
import com.jjjjisun.api_pratice.utills.RESPONSE_STATE
import com.jjjjisun.api_pratice.utills.SEARCH_TYPE
import com.jjjjisun.api_pratice.utills.onMyTextChanged
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 라디오 그룹 가져오기
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->

            // switch 문
            when (checkedId) {

                //사진검색 라디오 버튼 클릭
                R.id.radio_photo -> {
                    Log.d("tag", "사진검색 라디오 버튼 클릭")
                    binding.textLayout.hint = "사진검색"
                    binding.textLayout.startIconDrawable = resources.getDrawable(
                        R.drawable.ic_baseline_photo_library_24,
                        resources.newTheme()
                    )
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                //사용자검색 라디오 버튼 클릭
                R.id.radio_user -> {
                    Log.d("tag", "사용자검색 라디오 버튼 클릭")
                    binding.textLayout.hint = "사용자검색"
                    binding.textLayout.startIconDrawable = resources.getDrawable(
                        R.drawable.ic_baseline_person_24,
                        resources.newTheme()
                    )
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d("tag", "$currentSearchType")
        }

        // 텍스트가 변경이 되었을때
        binding.edSearch.onMyTextChanged {

            // 입력된 글자가 하나라도 있다면
            if (it.toString().count() > 0) {

                // 검색버튼을 보여줌
                frame_search_btn.visibility = View.VISIBLE
                // 헬퍼텍스트 지우기
                binding.textLayout.helperText = " "
                // 스크롤을 올림
                binding.mainScrollView.scrollTo(0, 200)

            } else {
                frame_search_btn.visibility = View.INVISIBLE  //검색버튼을 숨김
            }

            // 입력한 텍스트가 12자를 넘어간다면
            if (it.toString().count() == 12) {
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 검색 버튼 클릭 시시
        btn_search.setOnClickListener {
            Log.d("tag", "$currentSearchType")

            // 검색 api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = binding.edSearch.toString(), completion = {
                responseState, responseBody ->

                when(responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d("태그", "api 호출 성공 : $responseBody")
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                        Log.d("태그", "api 호출 실패 : $responseBody")
                    }
                }
            })

            this.handleSearchButtonUi()
        }

    } // onCreate

    private fun handleSearchButtonUi(){

        btn_progress.visibility = View.VISIBLE

        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.text = "검색"
        }, 1500)

    }







}