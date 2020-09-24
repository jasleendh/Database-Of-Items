package prog24178;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/*
 * @author Jasleen Dhillon
 */
public class DatabaseController implements Config {
	@FXML
	Label lblItem, lblId, lblQuantity, lblPrice, lblDisplayItem;
	@FXML
	TextField txtId, txtItem, txtQuantity, txtPrice;
	@FXML
	TextArea displayItem;
	@FXML
	Button btAddItem, btModifyItem, btRemoveItem, btLoad, btSave;

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<String> stringItems = new ArrayList<String>();
	@FXML
	ListView<String> list;

	File f = new File(file);

	// Check if file exists or not
	// If not then create a new file.
	public void createFile() {

		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	// On clicked btLoad (Load Data from file) load items
	// from items.txt file to listview
	public void loadDataFromFile() {
		createFile();
		items.clear();
		stringItems.clear();
		list.getItems().clear();
		Scanner lines;
		try {
			lines = new Scanner(f);
			while (lines.hasNextLine()) {
				Scanner line = new Scanner(lines.nextLine());
				line.useDelimiter(":\\s*");
				items.add(new Item(line.nextInt(), line.next(), line.nextInt(), line.nextDouble()));
				line.close();
			}
			lines.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (Item i : items) {
			list.getItems().add(i.toString());
		}
	}

	// On clicked btAddItem (Add Item) add item to listview
	public void addItem() {
		createFile();
		try {
			int id = Integer.parseInt(txtId.getText());
			String item = txtItem.getText();
			int quantity = Integer.parseInt(txtQuantity.getText());
			double price = Double.parseDouble(txtPrice.getText());
			items.add(new Item(id, item, quantity, price));

			list.getItems().clear();
			for (Item i : items) {
				list.getItems().add(i.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// On clicked display the data of selected
	// item in textfields from listview
	public void selectListItem() {
		createFile();
		Item showItem = items.get(list.getSelectionModel().getSelectedIndex());
		txtId.setText(Integer.toString(showItem.getId()));
		txtItem.setText(showItem.getItem());
		txtQuantity.setText(Integer.toString(showItem.getQuantity()));
		txtPrice.setText(Double.toString(showItem.getPrice()));
	}

	// Modify the item in the listview
	public void modifyItem() {
		createFile();
		int in = list.getSelectionModel().getSelectedIndex();
		int id = Integer.parseInt(txtId.getText());
		String item = txtItem.getText();
		int quantity = Integer.parseInt(txtQuantity.getText());
		double price = Double.parseDouble(txtPrice.getText());
		items.get(in).setId(id);
		items.get(in).setItem(item);
		items.get(in).setQuantity(quantity);
		items.get(in).setPrice(price);

		list.getItems().clear();
		for (Item i : items) {
			list.getItems().add(i.toString());
		}
	}

	// On clicked remove the item from listview
	// as well as from textfields displaying data
	public void removeItem() {
		createFile();
		items.remove(list.getSelectionModel().getSelectedIndex());
		txtId.setText("");
		txtItem.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");

		list.getItems().clear();
		for (Item i : items) {
			list.getItems().add(i.toString());
		}
	}

	// On clicked btSave (Save Data to File) save listview items to items.txt file
	public void saveDataToFile() throws IOException {
		createFile();
		PrintWriter pw = new PrintWriter(f);
		for (Item i : items) {
			pw.printf("%d:%s:%d:%f\n", i.getId(), i.getItem(), i.getQuantity(), i.getPrice());
		}
		pw.close();
	}
}
