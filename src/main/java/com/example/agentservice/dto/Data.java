package com.example.agentservice.dto;

@lombok.Data
public class Data {
    public int id;
    public boolean del_flg;
    public boolean entity_cre_flg;
    public String sol_id;
    public String bacid;
    public String accountNo;
    public String acct_name;
    public String gl_code;
    public String product_code;
    public String acct_ownership;
    public Object frez_code;
    public Object frez_reason_code;
    public String acct_opn_date;
    public boolean acct_cls_flg;
    public int clr_bal_amt;
    public int un_clr_bal_amt;
    public String hashed_no;
    public boolean int_paid_flg;
    public boolean int_coll_flg;
    public Object lchg_user_id;
    public Object lchg_time;
    public String rcre_user_id;
    public String rcre_time;
    public String acct_crncy_code;
    public int lien_amt;
    public String product_type;
    public int cum_dr_amt;
    public int cum_cr_amt;
    public boolean chq_alwd_flg;
    public double cash_dr_limit;
    public double xfer_dr_limit;
    public double cash_cr_limit;
    public double xfer_cr_limit;
    public Object acct_cls_date;
    public String last_tran_date;
    public Object last_tran_id_dr;
    public String last_tran_id_cr;
    public boolean walletDefault;
    public Object lien_reason;
}
