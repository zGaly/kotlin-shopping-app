package ipca.aulas.shopping.ui.listItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ipca.aulas.shopping.R
import ipca.aulas.shopping.ui.models.ListItem
import ipca.aulas.shopping.ui.theme.ShoppingTheme

@Composable
fun RowListItem(
    modifier: Modifier = Modifier,
    listItem: ListItem
)  {
    Box (
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(painter = painterResource(getResourceIcon( listItem.icon?:0L)),
                contentDescription = null)

            Text(
                text = listItem.name ?: ""
            )
        }
    }
}

fun getResourceIcon( iconRef : Long)  :  Int{
    return when (iconRef) {
        0L -> R.drawable.baseline_architecture_24
        1L -> R.drawable.baseline_add_home_24
        2L -> R.drawable.baseline_account_balance_wallet_24
        else -> R.drawable.ic_launcher_foreground
    }
}

@Preview(showBackground = true)
@Composable
fun RowListItemPreview() {
    ShoppingTheme {
        RowListItem(
            listItem = ListItem(
                name = "Escola",
                icon = 0L
            )
        )
    }
}