package fr.eni.crt.cadox;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.databinding.FragmentListeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Liste_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Liste_Fragment extends Fragment
{
    private FragmentListeBinding binding;
    private ItemListAdapter      adapter;
    private Manager              manager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_liste,container,false);

        adapter = new ItemListAdapter(new ItemListAdapter.ArticleDiff(), new OnRecyclerViewEventListener()
        {
            @Override
            public void OnItemClick(Article article)
            {
                Toast.makeText(binding.getRoot().getContext(), article.toString(), Toast.LENGTH_SHORT).show();

                NavController navController = NavHostFragment.findNavController(Liste_Fragment.this);

                Liste_FragmentDirections.ActionListeFragmentToDetailsArticleFragment action = Liste_FragmentDirections.actionListeFragmentToDetailsArticleFragment(article);

                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });

        /* avec le binding afficher les info sur le layout fragmen_list ???*/
        binding.itemRecyclerView.setAdapter(adapter);
        binding.itemRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        binding.itemRecyclerView.setHasFixedSize(true);

        manager = Manager.getReference();
        manager.updateListArticle();
        List<Article> list = manager.getArticles();

        adapter.submitList(list);

        return binding.getRoot();
    }
}