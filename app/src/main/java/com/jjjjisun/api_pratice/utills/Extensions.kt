package com.jjjjisun.api_pratice.utills

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// 문자열이 제이슨 형태인지
fun String?.isJsonObject(): Boolean {
    if (this?.startsWith("{") == true && this.endsWith("}")) {
        return true
    } else {
        return false
    }

}

// 문자열이 제이슨 배열인지

// 에딧 텍스트에 대한 익스텐션
fun EditText.onMyTextChanged(completion: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        // 글자가 바뀌기 전
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        // 글자가 변경될 때
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        // 글자가 바뀐 뒤
        override fun afterTextChanged(editable: Editable?) {
            completion(editable)
        }

    })

}