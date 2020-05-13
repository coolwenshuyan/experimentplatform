package com.coolwen.experimentplatform.model;

import javax.persistence.*;

    @Entity
    @Table(name = "t_teacher")
    public class Teacher {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "teacher_id")
        @TableGenerator(name = "teacher_id", initialValue = 0, allocationSize = 1,table = "seq_table")
        private int id;
        private String person_name;
        private String image_url;
        private String intro;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "id=" + id +
                    ", person_name='" + person_name + '\'' +
                    ", image_url='" + image_url + '\'' +
                    ", intro='" + intro + '\'' +
                    '}';
        }
    }



