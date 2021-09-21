package geekbrains.ru.history.view.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import geekbrains.ru.core.BaseActivity
import geekbrains.ru.history.R
import geekbrains.ru.history.databinding.ActivityHistoryBinding
import geekbrains.ru.history.injectDependencies
import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.DataModel
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        var word = intent.extras?.get(SEARCH_WORD_EXTRA).toString()
        if (word.isNullOrEmpty()) {
            word = ""
        } else {
            if (word == "null") word = ""
        }
        model.getData(word, false)
//        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        injectDependencies()
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    companion object {
        private const val SEARCH_WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b1"

        fun getIntent(
            context: Context,
            word: String
        ): Intent = Intent(context, HistoryActivity::class.java).apply {
            putExtra(SEARCH_WORD_EXTRA, word)
        }
    }
}
