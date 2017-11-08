/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.form;

import com.dulval.stetoskop.domain.InterationMedicament;
import com.dulval.stetoskop.domain.Medicament;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class MedicamentForm {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 2, max = 120, message = "O tamanho deve ser entre 2 e 120 caracteres")
    private String name;

    private Map<Integer, String> interations = new HashMap<>();

    private Set<String> apresentations = new HashSet<>();

    private Set<String> comercialNames = new HashSet<>();

    public MedicamentForm(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MedicamentForm() {
    }

    public Medicament build() {
        Medicament med = new Medicament();
        med.setId(this.id);
        med.setApresentations(this.apresentations);
        med.setComercialNames(this.comercialNames);
        med.setName(this.name);

        for (Integer key : interations.keySet()) {
            Medicament medInt = new Medicament(key, null);
            InterationMedicament interation = new InterationMedicament(med, medInt, interations.get(key));
            med.getInterations().add(interation);
        };
        return med;
    }

}
