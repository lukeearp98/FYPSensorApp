package com.example.gasmonitorapplication

import java.io.Serializable

class MemberDTO : Serializable {
    val id: Int
    val name: String?
    val username: String?
    val password: String?

    constructor() {
        id = 0
        name = ""
        username = ""
        password = ""
    }

    constructor(id: Int, name: String?, username: String?, password: String?) {
        this.id = id
        this.name = name
        this.username = username
        this.password = password
    }

    fun passwordMatches(pwd: String): Boolean {
        return password == pwd
    }
    fun getLoggedInName(memberDTO: MemberDTO) : String? {
        return getName(memberDTO);
    }
    fun getName(memberDTO: MemberDTO): String? {
        return memberDTO.name
    }
}
