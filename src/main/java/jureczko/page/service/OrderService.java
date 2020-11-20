package jureczko.page.service;

import jureczko.page.data.OrderRepository;
import jureczko.page.objects.Order;
import jureczko.page.objects.Slideshow;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
