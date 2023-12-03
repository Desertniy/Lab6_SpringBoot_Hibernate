package Desertniy.Lab5.service;

import Desertniy.Lab5.model.PropertyBody;
import Desertniy.Lab5.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository pr;

    public Iterable<PropertyBody> getObjects(){
        return pr.findAll();
    }
    public void addProperty(PropertyBody pd){
        pr.save(pd);
    }

    public PropertyBody getObj(String street){
        return pr.findByStreet(street);
    }

    @Transactional
    public void remove(String street){
        Optional<PropertyBody> optpb = Optional.ofNullable(pr.findByStreet(street));
        if(optpb.isPresent()){
            pr.deleteByStreet(street);
        }
    }

    @Transactional
    public void update_obj(PropertyBody pb){
        Optional<PropertyBody> optpb = Optional.ofNullable(pr.findByStreet(pb.getStreet()));
        if (optpb.isPresent()){
            PropertyBody newPb = optpb.get();
            newPb.setPrice(pb.getPrice());
            newPb.setMonthlyRent(pb.getMonthlyRent());
            pr.save(newPb);
        }
        else {
            pr.save(pb);
        }
    }
}
