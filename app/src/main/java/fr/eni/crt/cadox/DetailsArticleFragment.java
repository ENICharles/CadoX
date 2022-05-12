package fr.eni.crt.cadox;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.Date;

import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.databinding.FragmentDetailsArticleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsArticleFragment extends Fragment
{
    private Article article = null;

    private FragmentDetailsArticleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        Log.i("modification","ici");

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details_article,container,false);

        /* récupération d'une instance du manager */
        Manager manager = Manager.getReference();

        /* récupération des arguments */
        DetailsArticleFragmentArgs args = DetailsArticleFragmentArgs.fromBundle(getArguments());
        article = args.getArticle();
        Toast.makeText(DetailsArticleFragment.this.getActivity(),article.getDescription(), Toast.LENGTH_LONG).show();

        if(article != null)
        {
            /* gestion du label date */
            checkDateAchat();

            Log.i("modification","mise à jour de l'affichage");
            /* attribution des valeurs */
            binding.setArticleVue(article);
        }
        else
        {
            Toast.makeText(DetailsArticleFragment.this.getActivity(),"Oups pas d'article", Toast.LENGTH_LONG).show();
        }

        /* récupération des évènements sur les boutons */
        binding.buttonInternet.setOnClickListener((v ->
        {
            /* création d'une "popup furtive" */
            Toast.makeText(DetailsArticleFragment.this.getActivity(), binding.link.getText(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.link.getText().toString()));

            startActivity(intent);

        }));

        binding.acheteArticle.setOnClickListener((view ->
        {
            if(binding.acheteArticle.isChecked())
            {
                /* mise à la date du jour */
                article.setDateAchat(LocalDate.now());
                article.setAchete(true);
            }
            else
            {
                article.setAchete(false);
            }

            Log.i("modification","grrr");
            /* mise à jour de l'article */
            manager.updateArticle(article);

            checkDateAchat();
        }));

        binding.buttonEdite.setOnClickListener((v ->
        {
            /* création de la boite de dialogue */
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailsArticleFragment.this.getActivity());

            builder.setMessage(getString(R.string.editeMessage))
                    .setTitle(getString(R.string.editeMessageTitle));

            builder.setPositiveButton(R.string.txtYes, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    //Toast.makeText(DetailsArticleFragment.this.getActivity(), "Modification de l'article", Toast.LENGTH_LONG).show();
                    NavController navController = NavHostFragment.findNavController(DetailsArticleFragment.this);

                    DetailsArticleFragmentDirections.ActionDetailsArticleFragmentToModificationArticleFragment action = DetailsArticleFragmentDirections.actionDetailsArticleFragmentToModificationArticleFragment(article);

                    Navigation.findNavController(v).navigate(action);

                    /*navigation sans arguments */
//                    Navigation.findNavController(v).navigate(R.id.action_detailsArticleFragment_to_modificationArticleFragment);

                }
            });

            builder.setNegativeButton(R.string.txtNo, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    Toast.makeText(DetailsArticleFragment.this.getActivity(), "Pas de modification de l'article", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog dialog = builder.create();

            dialog.show();
        }));

        binding.buttonSMS.setOnClickListener((v ->
        {
            Log.i("mainAc", "sms");


            if (ContextCompat.checkSelfPermission(DetailsArticleFragment.this.getActivity().getBaseContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            {
                try
                {
                    Intent intent = new Intent();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("0647544713",null,"coucou de l'application",null,null);
                }
                catch(ActivityNotFoundException anfe)
                {
                    Toast.makeText(DetailsArticleFragment.this.getContext(), "Oups", Toast.LENGTH_LONG).show();
                }

            }
            else
            {
                Toast.makeText(DetailsArticleFragment.this.getContext(), "Oups2", Toast.LENGTH_LONG).show();
            }

/*            Snackbar.make(binding.prixArticle, "text example", Snackbar.LENGTH_LONG).
                    setAction(R.string.actionTexte, view ->
                    {
                        Toast.makeText(DetailsArticleFragment.this.getActivity(), "Envoie du sms", Toast.LENGTH_LONG).show();
                    })
                    .show();*/

        }));

        return binding.getRoot();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.i("modification","la");

        super.onCreate(savedInstanceState);

//        NavController navController = NavHostFragment.findNavController(this);
//
//        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();
//
//        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();

//        savedStateHandle.getLiveData("Article")
//                .observe(navBackStackEntry, article ->
//                {
////                    Toast.makeText(DetailsArticleFragment.this.getContext(), ((Article)article).getDescription().toString(), Toast.LENGTH_LONG).show();
////                    binding.setArticleVue((Article)article);
//                });
    }

    /**
     * Modifie l'affichage en fonction de l'article
     */
    private void checkDateAchat()
    {
        if(article.isAchete())
        {
            binding.labAchete.setText(getResources().getString(R.string.txtBYDate) + article.getDateAchat().toString());
        }
        else
        {
            binding.labAchete.setText("");
        }
    }
}