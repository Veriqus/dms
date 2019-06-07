package com.selfformat.deadmanswitch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import com.selfformat.deadmanswitch.base.CustomFragment
import com.selfformat.deadmanswitch.data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_emergency_sms.*

class EmergencySmsFragment : CustomFragment() {

    companion object {
        fun newInstance(): EmergencySmsFragment {
            return EmergencySmsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_emergency_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbarTitle?.text = resources.getString(R.string.set_emergency_contact)
        setEmergencyContact()
        saveContactButton.setOnClickListener {
            saveContact()
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun setEmergencyContact(){
        timeout.setText(sharedPref!!.getString(TIMEOUT_UNTIL_EMERGENCY_MESSAGE_KEY, DEFAULT_EMERGENCY_TIME.toString()))
        contactName.setText(sharedPref!!.getString(CONTACT_NAME_KEY, getString(R.string.sample_contact_name)))
        contactNumber.setText(sharedPref!!.getString(CONTACT_NUMBER_KEY, getString(R.string.example_phone_number)))
        emergencyMessage.setText(sharedPref!!.getString(EMERGENCY_MESSAGE_KEY, getString(R.string.default_emergency_message)))
    }

    //TODO: edittext fields validation

    private fun saveContact() {
        Log.i("EmergencySmsFragment", "Contact saved")
        sharedPref?.edit {
            putString(TIMEOUT_UNTIL_EMERGENCY_MESSAGE_KEY, timeout.text.toString())
            putString(CONTACT_NAME_KEY, contactName.text.toString())
            putString(CONTACT_NUMBER_KEY, contactNumber.text.toString())
            putString(EMERGENCY_MESSAGE_KEY, emergencyMessage.text.toString())
        }
    }
}