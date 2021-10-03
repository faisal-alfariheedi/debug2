package com.example.debug2

import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var llMain: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var rest: Button
    private lateinit var activeUsers: TextView

    private var users = arrayListOf(
        "Freddy",
        "Jason",
        "Ripley",
        "Poncho",
        "Saitama",
        "Archer",
        "Derek",
        "Pamela",
        "Simba",
        "Simon",
        "Retsy",
        "Peter",
        "Daria",
        "Smitty"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        rest=findViewById(R.id.res)
        submitBtn.setOnClickListener {
            if(usernameAccepted(userName.text.toString().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                && passwordAccepted(password.text.toString()) && pandp() ){
                Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                users.add(userName.text.toString()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                displayUsers()
            }
        }
        activeUsers = findViewById(R.id.tvActiveUsers)
        rest.setOnClickListener {
            again()
        }

    }



    fun pandp(): Boolean{
        if(password.text.toString()==password2.text.toString())return true
        Toast.makeText(this,"passwords doesn`t match",Toast.LENGTH_LONG).show()
        password2.setBackgroundColor(parseColor("#FF0000"))
        return false
    }

    private fun usernameAccepted(text: String): Boolean{
        if(text !in users){
            if(text.length in 5..15){
                if(!hasNumber(text)){
                    if(!hasSpecial(text) && !text.contains(" ")){
                        userName.setBackgroundColor(parseColor("#00FF00"))
                        return true
                    }else
                    Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_LONG).show()
                }else
                Toast.makeText(this, "The username cannot contain numbers", Toast.LENGTH_LONG).show()
            }else
            Toast.makeText(this, "The username must be between 5 and 15 characters long", Toast.LENGTH_LONG).show()
        }else
        Toast.makeText(this, "The username is already taken", Toast.LENGTH_LONG).show()
        userName.setBackgroundColor(parseColor("#FF0000"))

        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        if(text.length >= 8){
            if(hasUpper(text)){
                if(hasNumber(text)){
                    if(hasSpecial(text)){
                        password.setBackgroundColor(parseColor("#00FF00"))
                        return true
                    }else
                    Toast.makeText(this, "The password must contain a special character", Toast.LENGTH_LONG).show()
                }else
                Toast.makeText(this, "The password must contain a number", Toast.LENGTH_LONG).show()
            }else
            Toast.makeText(this, "The password must contain an uppercase letter", Toast.LENGTH_LONG).show()
        }else
        Toast.makeText(this, "The password must be at least 8 characters long", Toast.LENGTH_LONG).show()
        password.setBackgroundColor(parseColor("#FF0000"))
        return false
    }

    private fun hasUpper(text: String): Boolean{
        var letter = 'A'
        while (letter <= 'Z') {
            if(text[0] == letter){
                return true
            }
            ++letter
        }
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "~!@#$%^&*()-_=+[]{}<>,./*-+"
        for(special in specialCharacters){
            if(text.contains(special)){
                return true
            }
        }
        return false
    }

    private fun displayUsers(){
        var allUsers = "Active Users:\n\n"
        for(user in users){
            allUsers += user + "\n"
        }
        activeUsers.text = allUsers
        activeUsers.isVisible = true
        llMain.isVisible=false
        rest.isVisible=true
    }

    private fun again() {
        activeUsers.isVisible = false
        llMain.isVisible = true
        rest.isVisible=false
        userName.text.clear()
        password.text.clear()
        password2.text.clear()
        userName.setBackgroundColor(android.R.drawable.editbox_background_normal)
        password.setBackgroundColor(android.R.drawable.editbox_background)
        password2.setBackgroundColor(android.R.drawable.editbox_background)

    }
}