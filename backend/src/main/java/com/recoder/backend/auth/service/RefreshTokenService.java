package com.recoder.backend.auth.service;// package com.record.backend.auth.service;
//
// import java.time.Instant;
// import java.util.Optional;
// import java.util.UUID;
//
// //import com.record.backend.auth.domain.RefreshToken;
// //import com.record.backend.auth.repository.RefreshTokenRepository;
// import com.record.backend.auth.security.UserPrincipal;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.record.backend.exception.TokenRefreshException;
// import com.record.backend.repository.UserRepository;
//
// @Service
// public class RefreshTokenService {
//
// 	@Value("${app.jwtRefreshExpirationInMs}")
// 	private Long refreshTokenDurationMs;
//
// 	@Autowired
// 	private RefreshTokenRepository refreshTokenRepository;
//
// 	@Autowired
// 	private UserRepository userRepository;
//
// 	public Optional<RefreshToken> findByToken(String token) {
// 		return refreshTokenRepository.findByToken(token);
// 	}
//
// 	public RefreshToken createRefreshToken(Authentication authentication) {
//
// 		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
//
// 		RefreshToken refreshToken = new RefreshToken();
// 		refreshToken.setUser(userRepository.findById(userPrincipal.getId()).get());
// 		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
// 		refreshToken.setToken(UUID.randomUUID().toString());
// 		refreshToken = refreshTokenRepository.save(refreshToken);
// 		return refreshToken;
// 	}
//
// 	public RefreshToken verifyExpiration(RefreshToken token) {
// 		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
// 			refreshTokenRepository.delete(token);
// 			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
// 		}
// 		return token;
// 	}
//
// 	@Transactional
// 	public int deleteByUserId(Long userId) {
// 		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
// 	}
// }
