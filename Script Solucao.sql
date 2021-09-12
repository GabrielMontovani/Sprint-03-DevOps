Script solução - 


-- Criando o grupo de Recursos : Ambiente-Walle
--
az group create --name Ambiente-Walle --location eastus


-- IP gerado: 52.150.34.208
--
az vm create \
    --resource-group Ambiente-Walle \
    --name vm-walle \
    --image Oracle:Oracle-Linux:7.6:7.6.5 \
    --size Standard_B2ms \
    --vnet-name vnet-walle \
    --nsg nsgsr-walle \
	--public-ip-sku Standard \
    --storage-sku Standard_LRS \
    --os-disk-size-gb 64 \
    --authentication-type password \
    --admin-username walle \
    --admin-password  walle@Ramses

	Server IP: 52.150.34.208


-- Habilitando a portas:
--	
az network nsg rule create \
     --resource-group Ambiente-Walle \
     --nsg-name nsgsr-walle \
     --name AllowRDP \
     --protocol tcp \
     --priority 310 \
     --destination-port-range 3389
	
az network nsg rule create \
     --resource-group Ambiente-Walle \
     --nsg-name nsgsr-walle \
     --name Allow8080 \
     --protocol tcp \
     --priority 300 \
     --destination-port-range 8080
     
az network nsg rule create \
     --resource-group Ambiente-Walle \
     --nsg-name nsgsr-walle \
     --name Allow9090 \
     --protocol tcp \
     --priority 410 \
     --destination-port-range 9090
     
     
az network nsg rule create \
     --resource-group Ambiente-Walle \
     --nsg-name nsgsr-walle \
     --name Allow9000 \
     --protocol tcp \
     --priority 510 \
     --destination-port-range 9000          
	 
-- Instalar GUI maquina VM
--

cmd | Terminal | Remmina | 
ssh walle@52.150.34.208
pass: walle@Ramses

admin-username:  walle
admin-password:  walle@Ramses

--
-- (GNOME RH7)
--
sudo yum group list

--
-- Iniciando a instalação e configuração da GUI
-- 
sudo yum groupinstall "Server with GUI" -y


--
-- Verificando se a instalação da GUI está associada ao grupo
--
sudo yum group list

--
-- Atualizando o pacote Yum

sudo yum -y update

--
-- Download e install pacote epel onde se encontra o xRDP
--
sudo yum install https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm -y

--
-- Instalando o xRDP dentro do Oracle Linux 7.6
-- 
sudo yum install tigervnc-server xrdp

--
-- Realize o start do servico xrdp.service
--
sudo systemctl status xrdp.service
sudo systemctl start xrdp.service

--
-- Verifique o start do servico xrdp.service
--
sudo systemctl status xrdp.service

--
-- Habilitar permanentemente o servico xrdp.service
--
sudo systemctl enable xrdp.service

--
-- Verificando o xrdp e a porta 3389
--
sudo netstat -antp | grep xrdp

--
-- Verificando o xrdp e a porta 3389
--
sudo systemctl enable firewalld.service
sudo systemctl start firewalld.service
sudo systemctl status firewalld.service

--
-- Liberando e habilitando permanentemente a porta 3389 no firewall
--
sudo firewall-cmd --permanent --add-port=3389/tcp 
sudo firewall-cmd --reload

--
-- Restart do ambiente
--
sudo reboot


