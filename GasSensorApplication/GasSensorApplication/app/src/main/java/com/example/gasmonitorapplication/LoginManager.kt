package com.example.gasmonitorapplication

import android.content.Context
import android.content.SharedPreferences

class LoginManager private constructor(context: Context)
{
    internal var prefs: SharedPreferences
    internal var editor: SharedPreferences.Editor

    val isMemberLoggedIn: Boolean
        get() = prefs.getBoolean("MEMBER_IS_LOGGED_IN", false)


    init {
        prefs = context.getSharedPreferences("FYP", 0)
        editor = prefs.edit()
    }

    fun loginMember(user: MemberDTO) {
        editor.putBoolean("MEMBER_IS_LOGGED_IN", true)

        editor.putInt("MEMBER_ID", user.id)
        editor.putString("MEMBER_NAME", user.name)
        editor.putString("MEMBER_USERNAME", user.username)
        editor.putString("MEMBER_PASSWORD", user.password)

        editor.commit()
    }

    fun logout() {
        editor.putBoolean("MEMBER_IS_LOGGED_IN", false)
        editor.commit()
    }
    fun getLoggedInMember(): MemberDTO {
        return if (!isMemberLoggedIn) {
            MemberDTO()
        } else {
            MemberDTO(
                prefs.getInt("MEMBER_ID", 0),
                prefs.getString("MEMBER_NAME", ""),
                prefs.getString("MEMBER_USERNAME", ""),
                prefs.getString("MEMBER_PASSWORD", "")
            )
        }
    }
    companion object {

        var instance: LoginManager? = null

        fun getInstance(context: Context): LoginManager {
            if (instance == null)
                instance = LoginManager(context)

            return instance as LoginManager
        }


    }

}
