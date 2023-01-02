package com.aw.mdmtest

import android.content.Context
import android.content.RestrictionsManager
import android.widget.Toast
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    private var myRestrictionsMgr: RestrictionsManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun ShowAllReadMDM(username: String, email: String, password: String, company: String) {
        Column {
            Text("Username: $username", style = MaterialTheme.typography.bodyLarge)
            Text("Email: $email", style = MaterialTheme.typography.bodyLarge)
            Text("Password: $password", style = MaterialTheme.typography.bodyLarge)
            Text("Company: $company", style = MaterialTheme.typography.bodyLarge)
        }
    }

    override fun onResume() {
        super.onResume()
        myRestrictionsMgr = this.getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager
        // try to getusername here
        myRestrictionsMgr?.applicationRestrictions?.let {
            val username = it.getString("username") ?: "No username"
            val email = it.getString("email") ?: "No email"
            val password = it.getString("password") ?: "No password"
            val company = it.getString("company") ?: "No company"
            setContent { ShowAllReadMDM(username, email, password, company) }
        }

        if (myRestrictionsMgr == null) {
            Toast.makeText(this, "myRestrictionsMgr is null", Toast.LENGTH_LONG).show()
        } else if (myRestrictionsMgr!!.applicationRestrictions == null) {
            Toast.makeText(this, "myRestrictionsMgr.applicationRestrictions is null", Toast.LENGTH_LONG).show()
        } else if (!myRestrictionsMgr!!.applicationRestrictions!!.containsKey("username")) {
            Toast.makeText(this, "username is not there", Toast.LENGTH_LONG).show()
        }
    }
}