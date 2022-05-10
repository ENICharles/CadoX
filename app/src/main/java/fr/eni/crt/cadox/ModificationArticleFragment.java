package fr.eni.crt.cadox;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import fr.eni.crt.cadox.bll.Manager;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modification_article,container,false);

        /* récupération des arguments */
        ModificationArticleFragmentArgs args = ModificationArticleFragmentArgs.fromBundle(getArguments());

        //args.getArticle().setDescription("coucou");
        binding.setArticleVueModification(args.getArticle());

        binding.buttonSave.setOnClickListener((view ->
        {
            Article article = new Article(
                    1,
                    binding.articlename.getText().toString(),
                    binding.articledescriptif.getText().toString(),
                    Float.valueOf(binding.prixArticle.getText().toString()),
                    binding.acheteArticle.isChecked(),
                    (byte) binding.satisfactionArticle.getRating(),
                    binding.link.getText().toString(),
                    new Date());

            Log.i("modification","nouvelle informations2 id :|" +
                    1 + "| nom :|" +
                    binding.articlename.getText().toString() + "| description :|" +
                    binding.articledescriptif.getText().toString() + "| ischeck :|" +
                    binding.acheteArticle.isChecked() + "| rating :|" +
                    (byte) binding.satisfactionArticle.getRating() + "| link :|" +
                    binding.link.getText().toString() + "| date :|" +
                    new Date());

            /* mise à jour de l'article en base */
            Manager manager = Manager.getReference();
            manager.updateArticle(article);

            /* notification de la modification de l'article à la vue */
            NavController navController = NavHostFragment.findNavController(ModificationArticleFragment.this);

            navController.getPreviousBackStackEntry().getSavedStateHandle().set("Article",article);

            ModificationArticleFragment.this.getActivity().onBackPressed();

        }));

        return binding.getRoot();
    }
}