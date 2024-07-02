package components

import (
	"context"
	"encoding/json"
	"log"
	"net/http"
	"strings"
)

type key int

const (
	userIDKey key = iota
)

// Middleware de autenticação
func (svc *UserService) AuthMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		tokenString := r.Header.Get("Authorization")

		if tokenString == "" {
			w.WriteHeader(http.StatusUnauthorized)
			json.NewEncoder(w).Encode(&Response{Error: "Token de autenticação não fornecido"})
			return
		}

		tokenString = strings.TrimPrefix(tokenString, "Bearer ")

		claims, err := svc.validateJWT(tokenString)
		if err != nil {
			w.WriteHeader(http.StatusUnauthorized)
			json.NewEncoder(w).Encode(&Response{Error: "Token de autenticação inválido"})
			return
		}

		log.Printf("User ID from token: %s", claims.UserID)

		// Adicionar o UserID ao contexto da requisição
		ctx := context.WithValue(r.Context(), userIDKey, claims.UserID)
		r = r.WithContext(ctx)

		next.ServeHTTP(w, r)
	})
}
