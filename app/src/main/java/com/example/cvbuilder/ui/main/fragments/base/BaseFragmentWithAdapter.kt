package com.example.cvbuilder.ui.main.fragments.base

interface BaseFragmentWithAdapter<T, L, M> {
    var _binding: T?
    val binding get() = _binding!!
    var adapter: L
    var list : ArrayList<M>
}