package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.dto.data.ItemDTO;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.entities.data.Item;

@Service
public class ItemService {
    final private ItemRepository itemRepository;
    final private ProductService productService;

    public ItemService(ItemRepository itemRepository, ProductService productService) {
        this.itemRepository = itemRepository;
      this.productService = productService;
    }

   public void saveItemDTOAsItem(ItemDTO itemDTO){
       Item item = itemDTOToItem(itemDTO);

       itemRepository.save(item);
   }


    public Item itemDTOToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProduct(productService.productByName(itemDTO.getProduct().getName()));
        item.setIsSold(itemDTO.getIsSold());
        item.setIsDivided(itemDTO.getIsDivided());
        item.setNetPrice(itemDTO.getNetPrice());
        item.setParts(itemDTO.getParts());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setVat(itemDTO.getVat());
        itemRepository.save(item);
        return item;
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
