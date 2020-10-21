package com.example.demo.databasemock;

import com.example.demo.model.Education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EducationDataBaseMock {
    private static List<Education> educationsMock = Arrays.asList(
            new Education(1,2019,"user1 education1","The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form"),
            new Education(1,2018,"user1 education2","There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. "),
            new Education(2,2017,"user2 education1","If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. "),
            new Education(2,2015,"user2 education2","Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."),
            new Education(3,2011,"user3 education1","Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. "),
            new Education(4,2014,"user3 education1","All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.")
    );
    private static List<Education> educations = new ArrayList<>(educationsMock);
    public static List<Education> educationProvider() {
        return educations;
    }

    public static List<Education> getEducationsByUserId(long id) {
        return educations.stream().filter(education -> education.getUserId()==id).collect(Collectors.toList());
    }

    public static void add(Education education) {
        educations.add(education);
    }
}
