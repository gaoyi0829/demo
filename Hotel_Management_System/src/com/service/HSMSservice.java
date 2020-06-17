package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mapper.HSMSmapper;
import com.pojo.Complaint;
import com.pojo.Condition;
import com.pojo.Employee;
import com.pojo.PageTool;
import com.pojo.Praise;
import com.pojo.SpecialCase;
import com.pojo.Suggestion;
import com.pojo.Supply;

public class HSMSservice {
    private HSMSmapper hsmsmapper;


    public HSMSmapper getMapper() {
        return hsmsmapper;
    }

    public void setMapper(HSMSmapper hsmsmapper) {
        this.hsmsmapper = hsmsmapper;
    }

    //SelectEmps
    public List<Employee> showEmp(){
        System.out.println(hsmsmapper.selectAllEmp());
        return hsmsmapper.selectAllEmp();
    }
    public int showEmpCount() {

        return 	hsmsmapper.selectEmpCount();

    }
    public List<Employee> showEmpPage(PageTool pt) {
        return 	hsmsmapper.selectEmpPage(pt);

    }
    //SearchEmployee
    public Employee searchEmp(Condition cond){
        return hsmsmapper.searchEmp(cond);
    }
    public int showSEmpCount(Condition cond) {
        return 	hsmsmapper.selectSEmpCount(cond);
    }
    //SelectPraise

    public List<Praise> showPraPage(PageTool pt){
        return hsmsmapper.selectPraPage(pt);

    }
    public int showPraCount(PageTool pt) {

        return 	hsmsmapper.selectPraCount(pt);

    }
    //SelectSuggestion
    public List<Suggestion> showSugPage(PageTool pt){
        return hsmsmapper.selectSugPage(pt);

    }
    public int showSugCount(PageTool pt) {

        return 	hsmsmapper.selectSugCount(pt);

    }
    //SelectComplaint
    public List<Complaint> showCompPage(PageTool pt){
        return hsmsmapper.selectCompPage(pt);

    }
    public int showCompCount(PageTool pt) {

        return 	hsmsmapper.selectCompCount(pt);

    }

    //GiveSuggestion
    public void giveSuggestion(Suggestion sug) {
        hsmsmapper.giveSuggestion(sug);

    }
    //GivePraise
    public void givePraise(Praise pra) {
        hsmsmapper.givePriase(pra);
    }
    //GivePraise
    public void giveComplaint(Complaint comp) {
        hsmsmapper.giveComplaint(comp);
    }
    //AddEmployee
    public List<Integer> selectId() {
        return hsmsmapper.selectId();

    }
    public void addEmployee(Employee emp) {
        hsmsmapper.addEmployee(emp);
    }
    //DeleteEmployee
    public void delEmployee(Employee emp) {
        hsmsmapper.delEmployee(emp);
    }
    public void delEmployees(@Param("ids") List<Integer> ids) {
        hsmsmapper.delEmployees(ids);
    }
    //DeletePraise
    public int ifDelPraise(Praise pra) {
        return hsmsmapper.ifDelPraise(pra);
    }
    public void delPraise(Praise pra) {
        hsmsmapper.delPraise(pra);
    }

    //DeleteSuggestion
    public int ifDelSuggestion(Suggestion sug) {
        return hsmsmapper.ifDelSuggestion(sug);
    }
    public void delSuggestion(Suggestion sug) {
        hsmsmapper.delSuggestion(sug);
    }

    //DeleteComplaint
    public int ifDelComplaint(Complaint comp) {
        return hsmsmapper.ifDelComplaint(comp);
    }
    public void delComplaint(Complaint comp) {
        hsmsmapper.delComplaint(comp);
    }
    //UpdateEmployee
    public void updateEmployee(Employee emp) {
        hsmsmapper.updateEmployee(emp);
    }


    //AddSupply
    public List<Integer> selectSId() {
        return hsmsmapper.selectSId();

    }
    public void addSupply(Supply supp) {
        hsmsmapper.addSupply(supp);
    }
    //UpdateSupply
    public void updateSupply(Supply supp) {
        hsmsmapper.updateSupply(supp);
    }
    //DeleteSupply
    public void delSupply(Supply supp) {
        hsmsmapper.delSupply(supp);
    }
    public void delSupplies(@Param("ids") List<Integer> ids) {
        hsmsmapper.delSupplies(ids);
    }

    //SelectSupply
    public List<Supply> showSupp(){
        System.out.println(hsmsmapper.selectAllSupp());
        return hsmsmapper.selectAllSupp();
    }
    public int showSuppCount() {

        return 	hsmsmapper.selectSuppCount();

    }
    public List<Supply> showSuppPage(PageTool pt) {
        return 	hsmsmapper.selectSuppPage(pt);

    }
    //SearchSupply
    public List<Supply> searchSupp(Condition cond){
        return hsmsmapper.searchSupp(cond);
    }
    public int showSSuppCount(Condition cond) {
        return 	hsmsmapper.selectSSuppCount(cond);
    }

    //AddSpecial
    public List<Integer> selectSCId() {
        return hsmsmapper.selectSCId();

    }
    public void addSpecial(SpecialCase spe) {
        hsmsmapper.addSpecial(spe);
    }
    //UpdateSpecial
    public void updateSpecial(SpecialCase spe) {
        hsmsmapper.updateSpecial(spe);
    }
    //DeleteSpecial
    public void delSpecial(SpecialCase spe) {
        hsmsmapper.delSpecial(spe);
    }
    public void delSpecials(@Param("ids") List<Integer> ids) {
        hsmsmapper.delSpecials(ids);
    }

    //SelectSpecial
    public List<SpecialCase> showSpe(){
        System.out.println(hsmsmapper.selectAllSpe());
        return hsmsmapper.selectAllSpe();
    }
    public int showSpeCount() {

        return 	hsmsmapper.selectSpeCount();

    }
    public List<SpecialCase> showSpePage(PageTool pt) {
        return 	hsmsmapper.selectSpePage(pt);

    }
    //SearchSpecial
    public List<SpecialCase> searchSpe(Condition cond){
        return hsmsmapper.searchSpe(cond);
    }
    public int showSSpeCount(Condition cond) {
        return 	hsmsmapper.selectSSpeCount(cond);
    }
}
