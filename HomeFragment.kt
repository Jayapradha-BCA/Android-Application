package com.example.bingebuddybca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bingebuddybca.activities.AllMovieActivity
import com.example.bingebuddybca.adapter.BingeAdapter
import com.example.bingebuddybca.adapter.CategoryAdapter
import com.example.bingebuddybca.adapter.NextAdapter
import com.example.bingebuddybca.databinding.FragmentHomeBinding
import com.example.bingebuddybca.model.Category
import com.example.bingebuddybca.model.Movies
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    //category
    private lateinit var categoryAdapter: CategoryAdapter
    var categoryList = mutableListOf<Category>()

    //Binge
    private lateinit var bingeAdapter: BingeAdapter
    var bingeList = mutableListOf<Movies>()

    //Next
    private lateinit var nextAdapter: NextAdapter
    var nextList = mutableListOf<Movies>()


    private lateinit var firebaseFirestore: FirebaseFirestore
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseFirestore = FirebaseFirestore.getInstance()

        initRecyclerView()
        navigationOfViews()

        getCategoryDataFromFirestore()
        getBingeDataFromFirestore()
        getNextDataFromFirestore()

        return view
    }

    private fun navigationOfViews() {
        binding.tvSeeAllCategories.setOnClickListener {
            UtilObject.screenNavigation(requireContext(),
                AllMovieActivity::class.java)
        }
        binding.tvSeeAllBinge.setOnClickListener {
            UtilObject.screenNavigation(requireContext(),
                AllMovieActivity::class.java)
        }
        binding.tvSeeAllNext.setOnClickListener {
            UtilObject.screenNavigation(requireContext(),
                AllMovieActivity::class.java)
        }
    }

    private fun getNextDataFromFirestore() {
        firebaseFirestore.collection("Next")
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result){
                        val next = document.toObject(Movies::class.java)
                        nextList.add(next)
                        nextAdapter.notifyItemInserted(document.data.size)
                    }
                }else{
                    Toast.makeText(requireContext(), "Error Fetching Document!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun getBingeDataFromFirestore() {
        firebaseFirestore.collection("Binge")
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val binge = document.toObject(Movies::class.java)
                        bingeList.add(binge)
                        bingeAdapter.notifyItemInserted(document.data.size)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error Fetching Document!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }


    private fun getCategoryDataFromFirestore() {
        firebaseFirestore.collection("Category").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    for (document in task.result){
                        val category = document.toObject(Category::class.java)
                        categoryList.add(category)
                        categoryAdapter.notifyItemInserted(document.data.size)
                    }
                }   else{
                    Toast.makeText(requireContext(), "Error Getting Documents",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun initRecyclerView() {
        //category
        categoryAdapter = CategoryAdapter(requireContext(), categoryList)
        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvCategory.adapter = categoryAdapter

        //Binge
        bingeAdapter = BingeAdapter(requireContext(),bingeList)
        binding.rvBingeWorthy.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        binding.rvBingeWorthy.adapter = bingeAdapter

        //next
        nextAdapter = NextAdapter(requireContext(),nextList)
        binding.rvWatchNext.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvWatchNext.adapter = nextAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
