package uz.alien.task71.employer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import uz.alien.task71.ActivityEmployer
import uz.alien.task71.R
import uz.alien.task71.databinding.ItemEmployeeBinding

class AdapterEmployee : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private val employees = ArrayList<Employer>()

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEmployeeBinding.bind(view)
    }

    override fun getItemCount() = employees.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = EmployeeViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_employee,
                parent,
                false
            )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is EmployeeViewHolder) {

            holder.binding.root.setOnLongClickListener {
                dialogEmployee(employees[position], position)
                return@setOnLongClickListener false
            }

            holder.binding.tvName.text = employees[position].name
            holder.binding.tvAge.text = employees[position].age
            holder.binding.tvSalary.text = employees[position].salary + '$'
        }
    }

    fun add(employer: Employer, pos: Int = itemCount) {
        employees.add(pos, employer)
        notifyItemInserted(pos)
        notifyItemRangeChanged(pos, itemCount)
        log("$itemCount is added!")
    }

    fun delete(position: Int) {
        employees.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun dialogEmployee(employer: Employer, position: Int) {
        AlertDialog.Builder(ActivityEmployer.instance)
            .setTitle("Delete Post")
            .setMessage("Are you sure you want to delete this post?")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                EmployerRetrofit.delete(employer, position)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    fun clear() {
        employees.clear()
    }

    companion object {
        private const val TAG = "AdapterEmployee"
    }
}