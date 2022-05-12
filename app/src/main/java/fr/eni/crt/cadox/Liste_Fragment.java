package fr.eni.crt.cadox;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bll.RepositoryEventCalback;
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
    private Random rdm = new Random();
    private Handler handler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        /* récupération du thread principal */
        handler = Handler.createAsync(Looper.getMainLooper());

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

        /* avec le binding afficher les infos sur le layout fragmen_tlist ???*/
        binding.itemRecyclerView.setAdapter(adapter);
        binding.itemRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        binding.itemRecyclerView.setHasFixedSize(true);

        manager = Manager.getReference();

        manager.getArticles(new RepositoryEventCalback<List<Article>>()
        {
            @Override
            public void onComplete(List<Article> data)
            {
                adapter.submitList(data);
            }
        });

        //TODO ajout d'un nouvel élément dans la liste
        binding.floatingActionButton.setOnClickListener(v ->
        {
            NavController navController = NavHostFragment.findNavController(Liste_Fragment.this);

            Liste_FragmentDirections.ActionListeFragmentToModificationArticleFragment action = Liste_FragmentDirections.actionListeFragmentToModificationArticleFragment(null);
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

        return binding.getRoot();
    }
}