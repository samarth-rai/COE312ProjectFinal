
public abstract class AttackType {
     
    public Object attacker;

    public AttackType(Object s)
    {
        attacker = s;
    }

     abstract void Attack();
}
