package com.example.fasa7ni;

public interface RecyclerViewInterface
{
    void onItemClicked(int recycleViewID, int position);
    //I made recycleViewID to distinguish between different recyclerViews in HOME so it goes as follows
    //0->Places  , 1->Events, 2->Friends, 3->Request
}
