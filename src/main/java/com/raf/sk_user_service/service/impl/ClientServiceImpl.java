package com.raf.sk_user_service.service.impl;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.dto.*;
import com.raf.sk_user_service.exception.NotFoundException;
import com.raf.sk_user_service.mapper.ClientMapper;
import com.raf.sk_user_service.repository.ClientRepository;
import com.raf.sk_user_service.security.service.TokenService;
import com.raf.sk_user_service.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private TokenService tokenService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, TokenService tokenService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::clientToClientDto);
    }

    @Override
    public TokenResponseDto logIn(TokenRequestDto tokenRequestDto) {
        Client client = clientRepository.findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword()).
                orElseThrow(() -> new NotFoundException(String
                .format("Client with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                        tokenRequestDto.getPassword())));

        Claims claims = Jwts.claims();
        claims.put("id", client.getId());
        claims.put("role", client.getRole().getName());

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @SneakyThrows
    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::clientToClientDto).orElseThrow(() ->
                new NotFoundException("Client with id: " + id + " not found.")
        );
    }
    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto update(Long id, ClientUpdatedDto clientUpdatedDto) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client with id: " + id + " not found.")
        );
        client.setEmail(clientUpdatedDto.getEmail());
        client.setName(clientUpdatedDto.getName());
        client.setLastName(clientUpdatedDto.getLastName());
        client.setDateOfBirth(clientUpdatedDto.getDateOfBirth());
        client.setUsername(clientUpdatedDto.getUsername());

        return clientMapper.clientToClientDto(clientRepository.save(client));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto updatePassword(Long id, ClientUpdatePasswordDto clientUpdatePasswordDto) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client with id: " + id + " not found.")
        );

        client.setPassword(clientUpdatePasswordDto.getPassword());

        return clientMapper.clientToClientDto(clientRepository.save(client));
    }
}
