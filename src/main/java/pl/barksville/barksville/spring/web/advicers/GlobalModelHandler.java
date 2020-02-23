package pl.barksville.barksville.spring.web.advicers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.barksville.barksville.spring.session.OrderComponent;

@ControllerAdvice
public class GlobalModelHandler {

    private final OrderComponent orderComponent;

    public GlobalModelHandler(OrderComponent orderComponent) {
        this.orderComponent = orderComponent;
    }

    @ModelAttribute("currentOrder")
    public OrderComponent currentOrder() {
        return orderComponent;
    }
}
