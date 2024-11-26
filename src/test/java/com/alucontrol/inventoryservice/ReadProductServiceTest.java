package com.alucontrol.inventoryservice;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.exceptions.ProductNotFoundException;
import com.alucontrol.inventoryservice.repository.ProductRepository;
import com.alucontrol.inventoryservice.services.business.ReadProductServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// Essa abordagem está relacionada à inicialização de mocks e
// injeções de dependência simuladas na classe de teste.
// Sendo essencial para usar o Mockito de maneira simples e eficiente,
// pois elimina a necessidade de criar mocks manualmente.
@ExtendWith(MockitoExtension.class)
public class ReadProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ReadProductServices readProductServices;


    @Test
     void testHasSufficientStock_WhenStockIsSufficient() {

        Long productIdTest = 1L;
        int requestedQtyTest = 5;

        //Mock criado para retornar um Product com quantidade disponível maior ou igual à solicitada
        Product myProductTest = new Product();
        myProductTest.setQtyAvailable(50L);

        when(productRepository.findById(productIdTest)).thenReturn(Optional.of(myProductTest));

        boolean myResult = readProductServices.hasSufficientStock(productIdTest, requestedQtyTest);

        //Asserção: Verificamos se o método retorna true.
        assertTrue(myResult, "O produto precisa ter estoque suficiente");
        verify(productRepository).findById(productIdTest);

    }

    @Test
    void testHasSufficientStock_WhenStockIsNotSufficient() {

        Long productIdTest = 1L;
        int requestedQtyTest = 50;
        Product myProductTest = new Product();
        myProductTest.setQtyAvailable(7L);

        when(productRepository.findById(productIdTest)).thenReturn(Optional.of(myProductTest));

        boolean myResult = readProductServices.hasSufficientStock(productIdTest, requestedQtyTest);

        assertFalse(myResult, "A quantidade disponivel é inferior a solicitada");
        verify(productRepository).findById(productIdTest);
    }

    @Test
    void testHasSufficientStock_WhenProductDoesNotExist() {

        Long productIdTest = 1L;
        int requestedQtyTest = 50;

        when(productRepository.findById(productIdTest)).thenReturn(Optional.empty());

        ProductNotFoundException myException = assertThrows(ProductNotFoundException.class, () ->
                readProductServices.hasSufficientStock(productIdTest, requestedQtyTest));

        assertEquals("O produto não existe", myException.getMessage());
    }
}
