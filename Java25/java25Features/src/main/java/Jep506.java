
//Estudar mais

public class Jep506 {

    public class ContextoUsuario {

        private static final ScopedValue<String> USUARIO_LOGADO = ScopedValue.newInstance();

        public static void executarComUsuario(String nomeUsuario, Runnable action) {
            ScopedValue.where(USUARIO_LOGADO, nomeUsuario).run(action);
        }

        public static String getUsuarioLogado() {
            return USUARIO_LOGADO.get();
        }
    }

    public static class ServicePedido {
        public void processarPedido() {
            // Define o usuário no escopo
            ContextoUsuario.executarComUsuario("lee22br", () -> {
                criarPedido();
            });
        }

        private void criarPedido() {
            // Em qualquer lugar conseguimos o usuário sem passar parâmetro
            String usuario = ContextoUsuario.getUsuarioLogado();
            System.out.println("Pedido criado por: " + usuario);

            salvarNoBanco();
        }

        private void salvarNoBanco() {
            String usuario = ContextoUsuario.getUsuarioLogado();
            System.out.println("Salvando no banco para: " + usuario);
        }

        public static void main (){
            ServicePedido sp = new ServicePedido();
            sp.processarPedido();
        }
    }
}
