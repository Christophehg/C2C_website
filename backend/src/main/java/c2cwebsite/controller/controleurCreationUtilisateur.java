package c2cwebsite.controller;

import c2cwebsite.model.ISiteVente;
import c2cwebsite.pojo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controleurCreationUtilisateur {

    @Autowired
    ISiteVente siteVente;

    @RequestMapping("/CreationUtilisateur")
    public String creationUtilisateur(ModelMap pModel, @RequestParam("pPseudo") String pseudo,
                                      @RequestParam("pVilleResidence") String villeResidence,
                                      @RequestParam("pMdp") String mdp) {
        Utilisateur user = siteVente.creationUtilisateur(pseudo, mdp, villeResidence );
        pModel.addAttribute("Utilisateur", user);
        return "creationUtilisateurReponse";
    }



}
