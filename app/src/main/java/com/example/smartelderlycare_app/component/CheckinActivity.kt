package com.example.smartelderlycare_app.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartelderlycare_app.R

class CheckinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        val tvTotalPoints = findViewById<TextView>(R.id.tv_total_points)
        val tvConsecutiveDays = findViewById<TextView>(R.id.tv_consecutive_days)
        tvTotalPoints.text = "我的积分: 120"
        tvConsecutiveDays.text = "已连签: 3天"

        setupMockLeaderboard()
    }

    private fun setupMockLeaderboard() {
        val rvLeaderboard = findViewById<RecyclerView>(R.id.rv_leaderboard)

        val users = listOf(
            UserRank("李奶奶", 850),
            UserRank("王爷爷", 720),
            UserRank("赵阿姨", 610),
            UserRank("刘大爷", 430),
            UserRank("陈奶奶", 200),
            UserRank("我 (自己)", 120)
        )

        rvLeaderboard.layoutManager = LinearLayoutManager(this)
        rvLeaderboard.adapter = LeaderboardAdapter(users)
    }

    data class UserRank(val name: String, val points: Int)

    class LeaderboardAdapter(private val userList: List<UserRank>) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvRank: TextView = view.findViewById(R.id.tv_rank)
            val tvName: TextView = view.findViewById(R.id.tv_name)
            val tvPoints: TextView = view.findViewById(R.id.tv_points)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = userList[position]
            holder.tvRank.text = (position + 1).toString()
            holder.tvName.text = user.name
            holder.tvPoints.text = "${user.points} 分"

            when (position) {
                0 -> holder.tvRank.setTextColor(android.graphics.Color.parseColor("#FFD700"))
                1 -> holder.tvRank.setTextColor(android.graphics.Color.parseColor("#C0C0C0"))
                2 -> holder.tvRank.setTextColor(android.graphics.Color.parseColor("#CD7F32"))
                else -> holder.tvRank.setTextColor(android.graphics.Color.parseColor("#757575"))
            }
        }

        override fun getItemCount() = userList.size
    }
}