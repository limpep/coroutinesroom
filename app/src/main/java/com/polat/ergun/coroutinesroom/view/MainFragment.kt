package com.polat.ergun.coroutinesroom.view


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.polat.ergun.coroutinesroom.R
import com.polat.ergun.coroutinesroom.model.LoginState
import com.polat.ergun.coroutinesroom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameTV.text = LoginState.user?.username
        signoutBtn.setOnClickListener { onSignout() }
        deleteUserBtn.setOnClickListener { onDelete() }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "Signed out", Toast.LENGTH_SHORT).show()
            goToSignUpScreen()

        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "User deleted", Toast.LENGTH_SHORT).show()
            goToSignUpScreen()
        })
    }

    private fun goToSignUpScreen() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(usernameTV).navigate(action)
    }

    private fun onSignout() {
//        val action = MainFragmentDirections.actionGoToSignup()
//        Navigation.findNavController(usernameTV).navigate(action)

        viewModel.onSignout()
    }

    private fun onDelete() {
//        val action = MainFragmentDirections.actionGoToSignup()
//        Navigation.findNavController(usernameTV).navigate(action)
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete user")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes") { p0, p1 -> viewModel.onDeleteUser()}
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }

}
