Firebase Realtime Database

Ler dados: GET https://[DATABASE_NAME].firebaseio.com/[PATH].json
Escrever dados: PUT https://[DATABASE_NAME].firebaseio.com/[PATH].json
Atualizar dados: PATCH https://[DATABASE_NAME].firebaseio.com/[PATH].json
Excluir dados: DELETE https://[DATABASE_NAME].firebaseio.com/[PATH].json



Firebase Authentication

Registrar usuário: POST https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]
Login de usuário com email/senha: POST https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[API_KEY]
Login de usuário com token (por exemplo, Google): POST https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key=[API_KEY]
Enviar email de redefinição de senha: POST https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=[API_KEY]