/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg26_part26_tukhoathis;

/**
 *
 * @author NguyenVietTien
 */
public class Dog {
    private AnimalHouse animalhouse;
    private int _age;
    Dog(int age){
        this._age = age;
        animalhouse.AddDog(this);
    }
}
