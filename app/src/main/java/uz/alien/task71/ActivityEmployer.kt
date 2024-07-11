package uz.alien.task71

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.alien.task71.databinding.ActivityEmployeerBinding
import uz.alien.task71.employer.AdapterEmployee
import uz.alien.task71.employer.Employer
import uz.alien.task71.employer.EmployerRetrofit

class ActivityEmployer : AppCompatActivity() {

    lateinit var binding: ActivityEmployeerBinding
    var id = 2
    val adapterEmployee = AdapterEmployee()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityEmployeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instance = this

        binding.rvEmployes.layoutManager = LinearLayoutManager(this)
        binding.rvEmployes.isNestedScrollingEnabled = false
        binding.rvEmployes.adapter = adapterEmployee

        binding.root.setOnClickListener {
            EmployerRetrofit.getAll()
        }

        binding.bAddEmployee.setOnClickListener {
            EmployerRetrofit.create(Employer(0, "Khalilov Ibrohim", "100", "20"))
        }

        EmployerRetrofit.getAll()
    }

    fun showSnackbar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        lateinit var instance: ActivityEmployer
    }
}