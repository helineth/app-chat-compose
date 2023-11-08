package com.toquemedia.testecompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquemedia.testecompose.ui.theme.TesteComposeTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteComposeTheme {
                //  surface container usando a cor de 'fundo' do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation(messages = SampleData.conversationSample)
                }
            }
           // MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // dsipor os itens de dentro dele em linha
    Row(modifier = Modifier.padding(all = 8.dp)) {
    Image(
        // imagem em compose, o painter é como um src
        painter = painterResource(R.drawable._be01662_4689_41a0_814a_67f04f3e93a1),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            // a propriedade CircleShape torna o componente num círculo
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
    )
        // spacer para componentes ou textos em coluna como no chackra
    Spacer(modifier = Modifier.width(8.dp))


        //variável para ver se a mensagem está expandida ou não
        var isExpanded by remember { mutableStateOf(false) }

        // surfaceColor será atualizado gradualmente de uma cor para outra a medida que o estado vai sendo atualizado
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        // dsipor os itens de dentro dele em coluna
        // Alternamos o estado da variável "isExpanded" quando clicamos nesta coluna
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
        Text(
            text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium
        )
        // spacer para componentes ou textos em coluna como no chackra
        Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 1.dp,
                // surfaceColor, a cor mudará gradualmente do primário para a superfície
                color = surfaceColor,
                // animateContentSize, mudará o tamanho da superfície gradualmente
                modifier = Modifier.animateContentSize().padding(1.dp)

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // Se a mensagem for expandida, todo o seu conteúdo será expandido
                    // caso contrário, será exibida apenas a primeira linha
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
    }
}
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

//para se visualizar os dois modos
@Preview(
    showBackground = true,
    name = "Ligth Mode"
)
@Preview (
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Composable
fun DefaultPreview(){
    TesteComposeTheme {

            Conversation(messages = SampleData.conversationSample)
    }
}




