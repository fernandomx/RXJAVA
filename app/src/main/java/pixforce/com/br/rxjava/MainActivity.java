package pixforce.com.br.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void testar(View v) {

        //Pessoa p = new Pessoa("Fernando");

        Pessoa[] pessoas =  new Pessoa[10];
        for (int i=0; i< 10; i++) {
            pessoas[i] = new Pessoa("Pessoa " + i);
        }

        //Conceitos:
        //Observavel e observador:
        //Exemplo: Canal de tv paga (Obervavel) que emite um sinal, e quem vai receber o sinal é você (Observador). Esse sinal só é emitido
        //quando acontece uma assinatura do Observavel e também pode se desincrever dele para não receber mais sinal.


        //Objetivo é para programação assincrona


        //Observable<Pessoa> observable = Observable.just(p); // ira emitir uma pessoa

        Observable<Pessoa> observable = Observable.from(pessoas); // ira emitir um array com vários pessoas, uma pessoa por vez, ou poderá usar o just para receber tudo de uma vez tbm


        //interface 1 - possui somente um Callback que seria o onNext da interface 2
        Action1<Pessoa> observer = new Action1<Pessoa>() {
            @Override
            public void call(Pessoa pessoa) {
                Log.i(TAG,"call " + pessoa.getNome());
            }
        };


        //interface 2
        /*
        Observer<Pessoa> observer =  //observador do tipo pessoa
                new Observer<Pessoa>() { //callbacks
                    @Override // quando processo for completado
                    public void onCompleted() {
                        Log.i(TAG,"onCompleted");

                    }

                    @Override // executa quando ocorre um erro
                    public void onError(Throwable e) {

                    }

                    @Override // executa quando recebe uma pessoa
                    public void onNext(Pessoa pessoa) {
                        Log.i(TAG,"onNext " + pessoa.getNome());

                    }
                };

         */

        //inscrever um observador
        mSubscription =  observable.subscribe(observer); // interessante guardar referencia, apos feito uma requisição, para não receber mais dados da assinatura


    }

    @Override
    protected void onPause() {
        super.onPause();

        //mSubscription.unsubscribe(); // não receberá mais assinatura

    }
}