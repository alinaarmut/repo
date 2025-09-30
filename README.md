

## **Описание проекта**

Это REST API на Java + Spring Boot с базовой регистрацией, аутентификацией и получением пользователей.  
Проект демонстрирует реализацию безопасного кода и автоматизированный анализ уязвимостей через CI/CD (SpotBugs и Snyk).

---
## **API**

### Эндпоинты

| Метод | URL | Описание | Параметры |
|-------|-----|----------|-----------|
| `POST` | `/app/register` | Регистрация нового пользователя | JSON: `{ "username": "user1", "password": "pass123" }` |
| `POST` | `/app/login` | Аутентификация пользователя | JSON: `{ "username": "user1", "password": "pass123" }` |
| `GET` | `api/users` | Получение списка пользователей (только авторизованные) | JWT токен в заголовке `Authorization` |


**Пример запроса с curl:**

```bash
curl -X POST http://localhost:8080/app/register \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"pass123"}'
```
**Реализованные меры защиты:**

- Защита от SQLi (SQL-инъекций): Используются Spring Data JPA

- Защита от XSS: Токены в API возвращаются в формате JSON, который автоматически экранирует специальные символы. 

Также в коде используется HtmlUtils.htmlEscape() для безопасного отображения токенов в HTML и защиты от XSS.

- Защита от Broken Authentication: JWT-токены с подписью HMAC, проверка срока жизни токена, доступ к api/users только по токену.

**CI/CD:**

- SpotBugs: Статический анализ кода (SAST) для выявления потенциальных багов и антипаттернов.

- Snyk: Анализ зависимостей на известные уязвимости (SCA).

- GitHub Actions: Автоматический запуск сборки, тестов и анализа при push/pull request в ветки main


**Результаты:**

1. Spotbugs
<img width="1327" height="756" alt="Снимок экрана 2025-10-01 в 07 03 18" src="https://github.com/user-attachments/assets/677b660c-48f6-4c6d-b493-37862a0c6d95" />

2. 

<img width="1263" height="569" alt="Снимок экрана 2025-10-01 в 07 04 45" src="https://github.com/user-attachments/assets/a1c2aa70-94a5-45a1-8639-6c90e54f13b0" />

