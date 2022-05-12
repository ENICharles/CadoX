package fr.eni.crt.cadox;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.util.Date;

import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bll.RepositoryEventCalback;
import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.databinding.FragmentModificationArticleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificationArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificationArticleFragment extends Fragment
{
    private FragmentModificationArticleBinding binding;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        /* récupération du thread */
        handler = Handler.createAsync(Looper.getMainLooper());

        /* liaison de lavue avec le back */
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modification_article,container,false);

        /* récupération des arguments */
        ModificationArticleFragmentArgs args = ModificationArticleFragmentArgs.fromBundle(getArguments());

        /* asso de l'article passé(non obligatoir) en argument à la vue */
        binding.setArticleVueModification(args.getArticle());

        /* action sur le bt save */
        binding.buttonSave.setOnClickListener((view ->
        {
            /* création de l'article avec les information de l'écran/l'utilisateurs */
            Article article = new Article(
                    binding.articlename.getText().toString(),
                    binding.articledescriptif.getText().toString(),
                    Float.valueOf(binding.prixArticle.getText().toString()),
                    (byte) binding.satisfactionArticle.getRating(),
                    binding.link.getText().toString());

            /* mise à jour de l'article en base */
            Manager manager = Manager.getReference();

            /* détection si c'est une modification ou une création d'article */
            if(args.getArticle() == null)
            {
                /* ajout de l'article en base */
                manager.add(article,
                    new RepositoryEventCalback<Article>()
                    {
                        @Override
                        public void onComplete(Article data)
                        {
                            handler.post(()->
                            {
                                ModificationArticleFragment.this.getActivity().onBackPressed();
                            });
                        }
                    });
            }
            else
            {
                /* mise à jour de l'article en base  */
                manager.updateArticle(article);

                /* notification de la modification de l'article à la vue */
                NavController navController = NavHostFragment.findNavController(ModificationArticleFragment.this);

                navController.getPreviousBackStackEntry().getSavedStateHandle().set("Article",article);

                ModificationArticleFragment.this.getActivity().onBackPressed();
            }
        }));

        return binding.getRoot();
    }
}