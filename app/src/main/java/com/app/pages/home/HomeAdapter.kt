package com.app.pages.home

//class HomeAdapter(private val adapterOnClick: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//  private var operationalTimeList: ArrayList<OperationalTimeModel> = arrayListOf()
//
//  private var notificationList: ArrayList<NotificationModel> = arrayListOf()
//
//  override fun getItemCount(): Int = 3
//
//
//  override fun getItemViewType(position: Int): Int {
//    return position
//  }
//  var completionSelectedMenu: ((Int) -> Unit)? = null
//
//  var completionSeeMoreNotification: (() -> Unit)? = null
//  var completionSelectedClockInOut: ((isClockIn: Boolean) -> Unit)? = null
//
//  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//    val inflater = LayoutInflater.from(viewGroup.context)
//    if (viewType == 0) {
//      return HeaderViewHolder(inflater.inflate(R.layout.home_header_fragment, viewGroup, false))
//    } else if (viewType == 1) {
//      return MenuViewHolder(inflater.inflate(R.layout.home_menu_fragment, viewGroup, false))
//    } else {
//      return NotificationViewHolder(inflater.inflate(R.layout.home_notification_fragment, viewGroup, false))
//    }
//  }
//
//  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        Log.d("+++++++",UserConfig.getInstance().data.nama)
//    when (holder) {
//      is HeaderViewHolder -> {
//        holder.clockInButton.setOnClickListener {
//          completionSelectedClockInOut?.invoke(true)
//        }
//        holder.clockOutButton.setOnClickListener {
//          completionSelectedClockInOut?.invoke(false)
//        }
//        GlobalScope.async(Dispatchers.Main) {
//          holder.greetingTv.text = "Halo, ${UserConfig.getInstance().data.nama}\nSelamat Datang di Tunas Ruang Mesin"
//        }
//        val currentDate = (System.currentTimeMillis()/1000)
//        operationalTimeList.filter { (it.jabatan_id == UserConfig.getInstance().data.jabatan_id || it.jabatan_id == 0) && it.nama_hari_en.contains(currentDate.formatAsDate("EEEE"), true) }.firstOrNull().let {
//          holder.dateTimeTV.text = "${currentDate.formatAsDate("dd MMMM yyyy")} (${it?.wkt_in?.take(5)} - ${it?.wkt_out?.take(5)})"
//        }
//      }
//      is MenuViewHolder -> {
//        holder.history.setOnClickListener {
//          completionSelectedMenu?.invoke(0)
//        }
//
//        holder.timeOff.setOnClickListener {
//          completionSelectedMenu?.invoke(1)
//        }
//
//        holder.overtime.setOnClickListener {
//          completionSelectedMenu?.invoke(2)
//        }
//
//        holder.tracking.setOnClickListener {
//          completionSelectedMenu?.invoke(3)
//        }
//      }
//      is NotificationViewHolder -> {
//        var notificationAdapter = NotificationAdapter {}
////                holder.emptyView.isGone = !notificationList.isEmpty
//        notificationAdapter.setData(notificationList)
//        holder.recyclerAnnouncement.adapter = notificationAdapter
//        holder.seeMore.setOnClickListener {
//          completionSeeMoreNotification?.invoke()
//        }
//      }
//    }
//  }
//
//  inner class HomeHolder(view: View) : RecyclerView.ViewHolder(view) {
////        fun bindData() {
////
////        }
//  }
//
//  class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val clockInButton: TextView = itemView.findViewById(R.id.clockInButton)
//    val clockOutButton: TextView = itemView.findViewById(R.id.clockOutButton)
//    val greetingTv: TextView = itemView.findViewById(R.id.greetingMessage)
//    val dateTimeTV: TextView = itemView.findViewById(R.id.dateTime)
//  }
//
//  class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val history: ImageView = itemView.findViewById(R.id.history_view)
//    val timeOff: LinearLayout = itemView.findViewById(R.id.timeoff_view)
//    val overtime: LinearLayout = itemView.findViewById(R.id.overtime_view)
//    val tracking: LinearLayout = itemView.findViewById(R.id.tracking_view)
//
//  }
//
//  class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var recyclerAnnouncement: RecyclerView = itemView.findViewById(R.id.rvNotification)
//    var seeMore: TextView = itemView.findViewById(R.id.tvNotificationMore)
//    //        var emptyView: LinearLayout = itemView.findViewById(R.id.empty_view)
//    init {
//      var notificationAdapter = NotificationAdapter {}
//      recyclerAnnouncement.adapter = notificationAdapter
//      recyclerAnnouncement.layoutManager = LinearLayoutManager(itemView.context)
//    }
//  }
//
////    class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        var recyclerAnnouncement: RecyclerView = itemView.findViewById(R.id.rvAnnouncement)
////        init {
////            var homeAnnouncementAdapter = HomeAnnouncementAdapter {}
////            recyclerAnnouncement.adapter = homeAnnouncementAdapter
////            recyclerAnnouncement.layoutManager = LinearLayoutManager(itemView.context)
////        }
////    }
//
//  fun setOperationalTime() {
//    this.operationalTimeList = UserConfig.getInstance().operationalTimeList
//    GlobalScope.async(Dispatchers.Main) {
//      notifyItemChanged(0)
//    }
//  }
//
//  fun setNotification(data: ArrayList<NotificationModel>) {
//    this.notificationList = data
//    GlobalScope.async(Dispatchers.Main) {
//      notifyItemChanged(2)
//    }
//  }
//}
