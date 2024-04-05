package com.example.celebrityprofiles.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.example.celebrityprofiles.R
import com.example.celebrityprofiles.adapter.PersonAdapter
import com.example.celebrityprofiles.databinding.FragmentPersonListBinding
import com.example.celebrityprofiles.model.Person
import com.example.celebrityprofiles.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonListFragment : Fragment() {

    private var _binding: FragmentPersonListBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PersonListFragment()
    }

    private var adapter: PersonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PersonAdapter { person ->
            Toast.makeText(context, person.name, Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.adapter = adapter
//        fetchPersons("Ryan Gosling")


        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Вызывается, когда пользователь подтверждает поиск
                query?.let { fetchPersons(it) }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }

    private fun fetchPersons(query: String) {
        ApiClient.instance.fetchPersonList(query).enqueue(object : Callback<List<Person>> {
            override fun onResponse(
                call: Call<List<Person>>,
                response: Response<List<Person>>
            ) {
                if (response.isSuccessful) {
                    // Логируем успешный ответ
                    Log.d("PersonListFragment", "Success: ${response.body()}")
                    adapter?.submitList(response.body())
                } else {
                    // Логируем случай неуспешного ответа
                    Log.e("PersonListFragment", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                // Логируем ошибку подключения
                Log.e("PersonListFragment", "Failure: ${t.message}", t)
                Toast.makeText(context, "Ошибка подключения: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}